package com.simplemad.ad.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import com.mongodb.gridfs.GridFSDBFile;
import com.simplemad.ad.domain.AdPublishCondition;
import com.simplemad.ad.domain.AdStandard;
import com.simplemad.ad.domain.AdStatus;
import com.simplemad.ad.domain.Advertisement;
import com.simplemad.ad.domain.AdvertisementEffect;
import com.simplemad.ad.util.AdvertisementConvert;
import com.simplemad.base.dao.BaseDAO;
import com.simplemad.base.dao.BaseFAO;
import com.simplemad.base.domain.User;
import com.simplemad.base.util.CollectionUtil;
import com.simplemad.base.util.DateUtil;
import com.simplemad.base.util.StringUtil;
import com.simplemad.bean.AdvertisementType;
import com.simplemad.bean.Message;
import com.simplemad.bean.MessageType;
import com.simplemad.bean.NetAddress;
import com.simplemad.message.client.ClientHandlerAdapter;
import com.simplemad.message.client.IMessageClient;
import com.simplemad.message.client.UdpMobileClient;
import com.simplemad.message.util.AddressUtil;
import com.simplemad.message.util.JacksonUtil;
import com.simplemad.parameter.AdParameter;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	
	private final Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

	@Autowired
	private BaseFAO fao;
	
	@Autowired
	private BaseDAO dao;
	
	/**
	 * 广告有限期长为3天
	 */
	private static final int DAYS = 3;
	
	@Override
	public void create(Advertisement advertisement, File icon, File content) {
		try {
			advertisement.setCreatedDate(new Date());
			if(icon != null && icon.exists()) {
				ObjectId iconId = fao.createFile(icon);
				advertisement.setIconId(iconId.toString());
			}
			if(content != null && content.exists()) {
				ObjectId contentId = fao.createFile(content);
				advertisement.setContentId(contentId.toString());
			}
			advertisement.setStatus(AdStatus.NEW);
			dao.save(advertisement);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public InputStream findIconAsInputStream(String adId) {
		Advertisement advertisement = find(adId);
		if(StringUtil.isEmpty(advertisement.getIconId())) {
			return null;
		}
		return fao.findAsInputStream(new ObjectId(advertisement.getIconId()));
	}

	@Override
	public InputStream findContentAsInputStream(String adId) {
		Advertisement advertisement = dao.findOne(Advertisement.class, "id", new ObjectId(adId));
		if(StringUtil.isEmpty(advertisement.getContentId())) {
			return null;
		}
		return fao.findAsInputStream(new ObjectId(advertisement.getContentId()));
	}

	@Override
	public GridFSDBFile findIconAsFile(String adId) {
		Advertisement advertisement = find(adId);
		if(StringUtil.isEmpty(advertisement.getIconId())) {
			return null;
		}
		return fao.findAsFile(new ObjectId(advertisement.getIconId()));
	}

	@Override
	public GridFSDBFile findContentAsFile(String adId) {
		Advertisement advertisement = find(adId);
		if(StringUtil.isEmpty(advertisement.getContentId())) {
			return null;
		}
		return fao.findAsFile(new ObjectId(advertisement.getContentId()));
	}
	
	@Override
	public Advertisement find(String adId) {
		return dao.findOne(Advertisement.class, "id", new ObjectId(adId));
	}

	@Override
	public List<Advertisement> findAll() {
		return dao.findAll(Advertisement.class);
	}
	
	@Override
	public AdvertisementEffect publish(AdPublishCondition condition, User user, boolean isAdmin) {
		if(condition == null || condition.getAdvertisement() == null || user == null) {
			return null;
		}
		if(!condition.getAdvertisement().getStatus().equals(AdStatus.AD_PUBLISH_AUDIT_PASSED)) {
			if(!isAdmin) {
				return null;
			}
		}
		AdvertisementEffect effect = find(condition.getAdvertisement().getId().toString(), user);
		if(effect != null && effect.isReceivedMsg()) {
			return null;
		} else if(effect != null) {
			dao.delete(effect);
		}
		effect = new AdvertisementEffect();
		effect.setAdvertisement(condition.getAdvertisement());
		effect.setUser(user);
		effect.setCompleted(false);
		effect.setDownloaded(false);
		effect.setDownloading(false);
		effect.setOpened(false);
		effect.setReceivedMsg(false);
		effect.setShared(false);
		effect.setSharable(condition.isSharable());
		effect.setMoney(condition.getMoney());
		effect.setStartDate(new Date());
		effect.setEndDate(DateUtil.addDay(effect.getStartDate(), DAYS));
		dao.save(effect);
		return effect;
	}
	
	@Override
	public Advertisement addup(String adId) {
		Advertisement ad = find(adId);
//		ad.setReceivedQuantity(countQuantity(ad, "isReceivedMsg"));
//		ad.setDownloadingQuantity(countQuantity(ad, "isDownloading"));
//		ad.setDownloadedQuantity(countQuantity(ad, "isDownloaded"));
//		ad.setOpenedQuantity(countQuantity(ad, "isOpened"));
//		ad.setCompletedQuantity(countQuantity(ad, "isCompleted"));
//		ad.setSharedQuantity(countQuantity(ad, "isShared"));
//		ad.setSharedQuantityFee(countSharedQuantityFee(ad));
//		ad.setAdTotalMoney(adTotalMoney);
//		ad.setSharedTotalMoney(sharedTotalMoney);
		addup(ad);//TODO 暂用此方法,当量大时需要用MapReduce和以上注释了的方法进行查询
		return ad;
	}
	
	private void addup(Advertisement ad) {
		List<AdvertisementEffect> effects = findAllEffect(ad);
		for(AdvertisementEffect effect : effects) {
			if(effect.isReceivedMsg()) {
				ad.setReceivedQuantity(ad.getReceivedQuantity() + 1);
			}
			if(effect.isDownloading()) {
				ad.setDownloadingQuantity(ad.getDownloadingQuantity() + 1);
			}
			if(effect.isDownloaded()) {
				ad.setDownloadedQuantity(ad.getDownloadedQuantity() + 1);
			}
			if(effect.isOpened()) {
				ad.setOpenedQuantity(ad.getOpenedQuantity() + 1);
			}
			if(effect.isCompleted()) {
				ad.setCompletedQuantity(ad.getCompletedQuantity() + 1);
				ad.setAdTotalMoney(ad.getAdTotalMoney() + effect.getMoney().getTotalMoney4Ad());
			}
			if(effect.isShared()) {
				ad.setSharedQuantity(ad.getSharedQuantity() + 1);
			}
			if(effect.isSharable() && effect.isShared()) {
				ad.setSharedQuantityFee(ad.getSharedQuantityFee() + 1);
				ad.setSharedTotalMoney(ad.getSharedTotalMoney() + effect.getMoney().getTotalMoney4Sharing());
			}
			if(effect.getTimes() > 0) {
				ad.setTimes(ad.getTimes() + effect.getTimes());
			}
		}
	}
	
	@SuppressWarnings("unused")
	private long countSharedQuantityFee(Advertisement ad) {
		Query<AdvertisementEffect> query = dao.createQuery(AdvertisementEffect.class);
		return query.filter("advertisement", ad).filter("isShared", true)
				.filter("isSharable", true).countAll();
	}
	
	@SuppressWarnings("unused")
	private long countQuantity(Advertisement ad, String field) {
		Query<AdvertisementEffect> query = dao.createQuery(AdvertisementEffect.class);
		return query.filter("advertisement", ad).filter(field, true).countAll();
	}
	
	private AdvertisementEffect find(String adId, User user) {
		Advertisement ad = find(adId);
		return findEffect(ad, user);
	}

	@Override
	public boolean updateReceived(String adId, User user) {
		AdvertisementEffect effect = find(adId, user);
		if(effect == null) {
			return false;
		} else {
			effect.setReceivedMsg(true);
			AdvertisementType type = effect.getAdvertisement().getAdType();
			if(AdvertisementType.HTML.equals(type) || AdvertisementType.INTERACTION.equals(type)) {
				effect.setDownloading(false);
				effect.setDownloaded(true);
			}
			dao.save(effect);
			return true;
		}
	}

	@Override
	public boolean updateDownloading(String adId, User user) {
		AdvertisementEffect effect = find(adId, user);
		if(effect == null) {
			return false;
		} else {
			effect.setReceivedMsg(true);
			effect.setDownloading(true);
			AdvertisementType type = effect.getAdvertisement().getAdType();
			if(AdvertisementType.HTML.equals(type) || AdvertisementType.INTERACTION.equals(type)) {
				effect.setDownloading(false);
				effect.setDownloaded(true);
			}
			dao.save(effect);
			return true;
		}
	}

	@Override
	public boolean updateDownloaded(String adId, User user) {
		AdvertisementEffect effect = find(adId, user);
		if(effect == null) {
			return false;
		} else {
			effect.setReceivedMsg(true);
			effect.setDownloading(false);
			effect.setDownloaded(true);
			dao.save(effect);
			return true;
		}
	}

	@Override
	public boolean updateOpened(String adId, User user) {
		AdvertisementEffect effect = find(adId, user);
		if(effect == null) {
			return false;
		} else {
			effect.setReceivedMsg(true);
			effect.setDownloading(false);
			effect.setDownloaded(true);
			effect.setOpened(true);
			dao.save(effect);
			return true;
		}
	}

	@Override
	public boolean updateCompleted(String adId, User user) {
		AdvertisementEffect effect = find(adId, user);
		if(effect == null || effect.isCompleted() || effect.isOutOfDate()) {
			return false;
		} else {
			effect.setReceivedMsg(true);
			effect.setDownloading(false);
			effect.setDownloaded(true);
			effect.setOpened(true);
			effect.setCompleted(true);
			dao.save(effect);
			return true;
		}
	}

	@Override
	public boolean updateShared(String adId, User user) {
		AdvertisementEffect effect = find(adId, user);
		if(effect == null || effect.isShared() || effect.isOutOfDate()) {
			return false;
		} else {
			effect.setReceivedMsg(true);
			effect.setDownloading(false);
			effect.setDownloaded(true);
			effect.setOpened(true);
			effect.setCompleted(true);
			effect.setShared(true);
			dao.save(effect);
			return true;
		}
	}
	
	@Override
	public AdvertisementEffect findEffect(Advertisement ad, User user) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("advertisement", ad);
		conditions.put("user", user);
		List<AdvertisementEffect> effects = dao.findAll(AdvertisementEffect.class, conditions);
		if(CollectionUtil.isEmpty(effects) || effects.size() != 1) {
			return null;
		} else {
			return effects.get(0);
		}
	}

	@Override
	public List<AdvertisementEffect> findAllEffect(Advertisement ad) {
		return dao.findAll(AdvertisementEffect.class, "advertisement", ad);
	}

	@Override
	public List<User> findAvaiableUser(Advertisement ad, List<User> userList) {
		Query<AdvertisementEffect> query = dao.createQuery(AdvertisementEffect.class);
		query.field("advertisement").equal(ad).filter("isReceivedMsg", true);
		List<AdvertisementEffect> receivedEffect = query.asList();
		List<User> removingList = new ArrayList<User>();
		for(User user : userList) {
			for(AdvertisementEffect effect : receivedEffect) {
				if(user.sameIdentityAs(effect.getUser())) {
					removingList.add(user);
					break;
				}
			}
		}
		userList.removeAll(removingList);
		return userList;
	}

	@Override
	public boolean auditPass(String adId, AdStandard as) {
		Advertisement ad = find(adId);
		ad.setStandard(as);
		ad.setStatus(AdStatus.AD_AUDIT_PASSED);
		dao.save(ad);
		return true;
	}

	@Override
	public boolean auditNotPass(String adId) {
		Advertisement ad = find(adId);
		ad.setStatus(AdStatus.AD_AUDIT_NON_PASSED);
		dao.save(ad);
		return true;
	}

	@Override
	public boolean auditPublishPass(String adId) {
		Advertisement ad = find(adId);
		ad.setStatus(AdStatus.AD_PUBLISH_AUDIT_PASSED);
		dao.save(ad);
		return true;
	}

	@Override
	public boolean auditPublishNonPass(String adId) {
		Advertisement ad = find(adId);
		ad.setStatus(AdStatus.AD_PUBLISH_AUDIT_NON_PASSED);
		dao.save(ad);
		return true;
	}

	@Override
	public Advertisement submit(String adId, long publihQuantity) {
		Advertisement ad = find(adId);
		if(ad == null) {
			return null;
		}
		if(ad.getStatus().equals(AdStatus.AD_AUDIT_PASSED)) {
			ad.setPublishQuantity(ad.getPublishQuantity() + publihQuantity);
			ad.setStatus(AdStatus.AD_SUBMIT);
			dao.save(ad);
		}
		return ad;
	}

	@Override
	public void sendAd(AdvertisementEffect effect, boolean isAdmin) {
		Advertisement advertisement = effect.getAdvertisement();
		if(effect.isOutOfDate() || !advertisement.getStatus().equals(AdStatus.AD_PUBLISH_AUDIT_PASSED)) {
			if(!isAdmin) {
				return;
			}
		}
		User user = effect.getUser();
		if(user.getIpAddress() == null) {
			return;
		}
		if(effect.isReceivedMsg()) {
			return;
		}
		//发送至msgserver再转化到相应的客户端
		doSendAd(effect, advertisement, user);
	}

	private void doSendAd(AdvertisementEffect effect, Advertisement advertisement, User user) {
		InetSocketAddress host = (InetSocketAddress) user.getIpAddress();
		InetSocketAddress msgServerHost = new InetSocketAddress(AdParameter.UDP_HOST, AdParameter.UDP_PORT);
		
		Message msg = new Message();
		NetAddress target = AddressUtil.convert(host);
		msg.setTarget(target);
		msg.setSendDate(new Date());
		msg.setType(MessageType.AD);
		msg.setContent(JacksonUtil.toJSON(AdvertisementConvert.convert(advertisement, effect)));
		
		IMessageClient client = new UdpMobileClient();
		IoHandlerAdapter handler = new ClientHandlerAdapter();
		client.setHandler(handler);
		client.send(msgServerHost, null, msg);
	}

	@Override
	public List<AdvertisementEffect> findAvaiableEffect(User user) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("isReceivedMsg", false);
		conditionMap.put("user", user);
		return dao.findAll(AdvertisementEffect.class, conditionMap);
	}

	@Override
	public List<Advertisement> findAll(AdStatus status) {
		return dao.findAll(Advertisement.class, "status", status);
	}

	@Override
	public boolean updateTimes(String adId, User user, int times) {
		AdvertisementEffect effect = find(adId, user);
		if(effect == null) {
			return false;
		} else {
			effect.setTimes(times);
			dao.save(effect);
			return true;
		}
	}

	@Override
	public List<Advertisement> findAllNewTaskAuditPublishPassed() {
		Query<Advertisement> query = dao.createQuery(Advertisement.class);
		query.and(getNewTaskCriteria(query), getAuditPublishPassedCriteria(query));
		logger.debug(query.toString());
		return query.asList();
	}

	@Override
	public List<Advertisement> findAllNonNewTaskAuditPublishPassed() {
		Query<Advertisement> query = dao.createQuery(Advertisement.class);
		query.and(getAuditPublishPassedCriteria(query), getNonNewTaskCriteria(query));
		return query.asList();
	}
	
	private Criteria getNonNewTaskCriteria(Query<Advertisement> query) {
		Criteria free = query.criteria("standard").notEqual(AdStandard.NewTaskFree);
		Criteria fee = query.criteria("standard").notEqual(AdStandard.NewTaskFee);
		return query.and(free, fee);
	}
	
	private Criteria getAuditPublishPassedCriteria(Query<Advertisement> query) {
		return query.criteria("status").equal(AdStatus.AD_PUBLISH_AUDIT_PASSED);
	}
	
	private Criteria getNewTaskCriteria(Query<Advertisement> query) {
		Criteria free = query.criteria("standard").equal(AdStandard.NewTaskFree);
		Criteria fee = query.criteria("standard").equal(AdStandard.NewTaskFee);
		return query.or(free, fee);
	}

}
