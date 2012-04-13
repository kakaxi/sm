package com.simplemad.ad.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;
import com.simplemad.ad.domain.AdPublishCondition;
import com.simplemad.ad.domain.AdStandard;
import com.simplemad.ad.domain.AdStatus;
import com.simplemad.ad.domain.Advertisement;
import com.simplemad.ad.domain.AdvertisementEffect;
import com.simplemad.base.domain.User;

public interface AdvertisementService {

	public boolean auditPass(String adId, AdStandard as);
	
	public boolean auditNotPass(String adId);
	
	public boolean auditPublishPass(String adId);
	
	public boolean auditPublishNonPass(String adId);
	
	public Advertisement submit(String adId, long publishQuantity); 
	
	public void create(Advertisement advertisement, File icon, File content);
	
	public InputStream findIconAsInputStream(String adId);
	
	public InputStream findContentAsInputStream(String adId);
	
	public GridFSDBFile findIconAsFile(String adId);
	
	public GridFSDBFile findContentAsFile(String adId);
	
	public Advertisement find(String adId);
	
	public List<Advertisement> findAll();
	
	public List<Advertisement> findAll(AdStatus status);
	
	public List<Advertisement> findAllNewTaskAuditPublishPassed();
	
	public List<Advertisement> findAllNonNewTaskAuditPublishPassed();
	
	/**
	 * @param advertisement
	 * @param user
	 * @return false if it had been publish the advertisement to the user ever,otherwise true
	 */
	public AdvertisementEffect publish(AdPublishCondition condition, User user, boolean isAdmin);
	
	public void sendAd(AdvertisementEffect effect, boolean isAdmin);
	
	public Advertisement addup(String adId);
	
	public boolean updateReceived(String adId, User user);
	
	public boolean updateDownloading(String adId, User user);
	
	public boolean updateDownloaded(String adId, User user);
	
	public boolean updateOpened(String adId, User user);
	
	public boolean updateCompleted(String adId, User user);
	
	public boolean updateShared(String adId, User user);
	
	public boolean updateTimes(String adId, User user, int times);
	
	public AdvertisementEffect findEffect(Advertisement ad, User user);
	
	public List<AdvertisementEffect> findAllEffect(Advertisement ad);
	
	public List<User> findAvaiableUser(Advertisement ad, List<User> userList);
	
	public List<AdvertisementEffect> findAvaiableEffect(User user);
}
