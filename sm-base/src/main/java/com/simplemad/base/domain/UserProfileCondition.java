package com.simplemad.base.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.simplemad.base.domain.enums.BodyType;
import com.simplemad.base.domain.enums.CarType;
import com.simplemad.base.domain.enums.Degree;
import com.simplemad.base.domain.enums.DietTaste;
import com.simplemad.base.domain.enums.Gender;
import com.simplemad.base.domain.enums.Have;
import com.simplemad.base.domain.enums.Investment;
import com.simplemad.base.domain.enums.Job;
import com.simplemad.base.domain.enums.Marriage;
import com.simplemad.base.domain.enums.PhoneAppHobby;
import com.simplemad.base.domain.enums.Salary;
import com.simplemad.base.domain.enums.SpareHobby;
import com.simplemad.base.util.CollectionUtil;
import com.simplemad.base.util.DateUtil;
import com.simplemad.base.util.StringUtil;

public class UserProfileCondition implements Serializable {

	private static final long serialVersionUID = 857973399406330198L;

	/**condition 1*/
	private List<Gender> genders;
	
	private List<IntegerScope> ages;
	
	private List<AreaCondition> areas;
	
	private List<String> addresses;
	
	private List<Salary> salaries;
	
	private List<Job> jobs;
	
	/**condition 2*/
	private List<Marriage> marriages;
	
	private List<BodyType> bodies;
	
	private List<Degree> degrees;
	
	private List<Salary> familySalaries;
	
	private List<CarCondition> cars;
	
	private List<ChildCondition> childs;
	
	/**condition 3*/
	private List<Investment> investments;
	
	private List<SpareHobby> spareHobbies;
	
	private List<PhoneAppHobby> appHobbies;
	
	private List<DietTaste> tastes;
	
	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public List<IntegerScope> getAges() {
		return ages;
	}

	public void setAges(List<IntegerScope> ages) {
		this.ages = ages;
	}

	public List<AreaCondition> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaCondition> areas) {
		this.areas = areas;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public List<Salary> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Marriage> getMarriages() {
		return marriages;
	}

	public void setMarriages(List<Marriage> marriages) {
		this.marriages = marriages;
	}

	public List<BodyType> getBodies() {
		return bodies;
	}

	public void setBodies(List<BodyType> bodies) {
		this.bodies = bodies;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public List<Salary> getFamilySalaries() {
		return familySalaries;
	}

	public void setFamilySalaries(List<Salary> familySalaries) {
		this.familySalaries = familySalaries;
	}

	public List<CarCondition> getCars() {
		return cars;
	}

	public void setCars(List<CarCondition> cars) {
		this.cars = cars;
	}

	public List<ChildCondition> getChilds() {
		return childs;
	}

	public void setChilds(List<ChildCondition> childs) {
		this.childs = childs;
	}

	public List<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(List<Investment> investments) {
		this.investments = investments;
	}

	public List<SpareHobby> getSpareHobbies() {
		return spareHobbies;
	}

	public void setSpareHobbies(List<SpareHobby> spareHobbies) {
		this.spareHobbies = spareHobbies;
	}

	public List<PhoneAppHobby> getAppHobbies() {
		return appHobbies;
	}

	public void setAppHobbies(List<PhoneAppHobby> appHobbies) {
		this.appHobbies = appHobbies;
	}

	public List<DietTaste> getTastes() {
		return tastes;
	}

	public void setTastes(List<DietTaste> tastes) {
		this.tastes = tastes;
	}
	
	public boolean isEmpty() {
		return getCondionNum() == 0;
	}
	
	public int getCondionNum() {
		int count = 0;
		if(!CollectionUtil.isEmpty(getCars())) {
			for(CarCondition car : getCars()) {
				if(car != null && car.getHave() != null) {
					count++;
					break;
				}
			}
		}
		if(!CollectionUtil.isEmpty(getChilds())) {
			for(ChildCondition child : getChilds()) {
				if(child != null && child.getHave() != null) {
					count++;
					break;
				}
			}
		}
		if(!CollectionUtil.isEmpty(getAddresses())) {
			for(String address : getAddresses()) {
				if(!StringUtil.isEmpty(address)) {
					count++;
					break;
				}
			}
		} 
		if(!CollectionUtil.isEmpty(getAges())) {
			for(IntegerScope scope : getAges()) {
				if(scope != null && (scope.getMin() != null || scope.getMax() != null)) {
					count++;
					break;
				}
			}
		}
		if(!CollectionUtil.isEmpty(getAppHobbies())) {
			for(PhoneAppHobby hobby : getAppHobbies()) {
				if(hobby != null) {
					count++;
					break;
				}
			}
		} 
		if(!CollectionUtil.isEmpty(getAreas())) {
			for(AreaCondition area : getAreas()) {
				if(area != null && area.getId() != null) {
					count++;
					break;
				}
			}
			
		}
		if(!CollectionUtil.isEmpty(getTastes())) {
			for(DietTaste taste : getTastes()) {
				if(taste != null) {
					count++;
					break;
				}
			}
		} 
		if(!CollectionUtil.isEmpty(getSpareHobbies())) {
			for(SpareHobby hobby : getSpareHobbies()) {
				if(hobby != null) {
					count++;
					break;
				}
			}
		}
		if(!CollectionUtil.isEmpty(getJobs())) {
			for(Job job : getJobs()) {
				if(job != null) {
					count++; 
					break;
				}
			}
		} 
		if(!CollectionUtil.isEmpty(getInvestments())) {
			for(Investment investment : getInvestments()) {
				if(investment != null) {
					count++;
					break;
				}
			}
		}
		if(!CollectionUtil.isEmpty(getMarriages())) {
			for(Marriage marriage : getMarriages()) {
				if(marriage != null ) {
					count++;
					break;
				}
			}
		} 
		if(!CollectionUtil.isEmpty(getGenders())) {
			for(Gender gender : getGenders()) {
				if(gender != null) {
					count++;
					break;
				}
			}
		}
		if(!CollectionUtil.isEmpty(getFamilySalaries())) {
			for(Salary salary : getFamilySalaries()) {
				if(salary != null) {
					count++;
					break;
				}
			}
		} 
		if(!CollectionUtil.isEmpty(getDegrees())) {
			for(Degree degree :getDegrees()) {
				if(degree != null) {
					count++;
					break;
				}
			}
		}
		if(!CollectionUtil.isEmpty(getBodies())) {
			for(BodyType body : getBodies()) {
				if(body != null) {
					count++;
					break;
				}
			}
		} 
		if(!CollectionUtil.isEmpty(getSalaries())) {
			for(Salary salary : getSalaries()) {
				if(salary != null) {
					count++;
					break;
				}
			}
		}
		return count;
	}
	
	public static String[] view(UserProfileCondition condition) {
		List<String> result = new ArrayList<String>();
		genderView(condition, result);
		ageView(condition, result);
		areaView(condition, result);
		addressView(condition, result);
		salaryView(condition, result);
		jobView(condition, result);
		degreeView(condition, result);
		familySalaryView(condition, result);
		marriageView(condition, result);
		bodyView(condition, result);
		carView(condition, result);
		childView(condition, result);
		investmentView(condition, result);
		spareHobbyView(condition, result);
		appHobbyView(condition, result);
		dietTasteView(condition, result);
		
		return convert(result);
	}
	
	private static void childView(UserProfileCondition condition,
			List<String> result) {
		List<ChildCondition> children = condition.getChilds();
		if(!CollectionUtil.isEmpty(children)) {
			boolean isNull = true;
			for(ChildCondition child : children) {
				if(child.getHave() != null) {
					isNull = false;
					break;
				}
			}
			if(isNull) {
				return;
			}
			StringBuffer buffer = new StringBuffer();
			buffer.append("是否有孩子").append(" : ");
			for(int index = 0; index < children.size(); index++) {
				ChildCondition child = children.get(index);
				if(child.getHave() == null) {
					return;
				}
				if(child.getHave().equals(Have.HAVE)) {
					buffer.append("有").append("   ");
					String childAgeStr = childAgeView(child);
					if(!StringUtil.isEmpty(childAgeStr)) {
						buffer.append(childAgeStr).append("   ");
					}
					String genderStr = childGenderView(child);
					if(!StringUtil.isEmpty(genderStr)) {
						buffer.append(genderStr).append("   ");
					}
					String degreeStr = childDegreeView(child);
					if(!StringUtil.isEmpty(degreeStr)) {
						buffer.append(degreeStr).append("   ");
					}
				} else {
					buffer.append("否").append("   ");
				}
				if(index != children.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static String childDegreeView(ChildCondition condition) {
		List<Degree> degrees = condition.getDegrees();
		if(!CollectionUtil.isEmpty(degrees)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("学历").append(" --- ");
			for(int index = 0; index < degrees.size(); index++) {
				buffer.append(degrees.get(index).getName());
				if(index != degrees.size() - 1) {
					buffer.append(", ");
				}
			}
			return buffer.toString();
		}
		return null;
	}
	
	private static String childGenderView(ChildCondition condition) {
		if(!CollectionUtil.isEmpty(condition.getGenders())) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("性别").append(" --- ");
			for(int index = 0; index < condition.getGenders().size(); index++) {
				buffer.append(condition.getGenders().get(index).getName());
				if(index != condition.getGenders().size() - 1) {
					buffer.append(", ");
				}
			}
			return buffer.toString();
		}
		return null;
	}
	
	private static String childAgeView(ChildCondition condition) {
		if(!CollectionUtil.isEmpty(condition.getAges())) {
			boolean isNull = true;
			for(IntegerScope scope : condition.getAges()) {
				if(scope.getMin() != null || scope.getMax() != null) {
					isNull = false;
					break;
				}
			}
			if(isNull) {
				return "";
			}
			StringBuffer buffer = new StringBuffer();
			buffer.append("年龄段").append("   ");
			for(int index = 0; index < condition.getAges().size(); index++) {
				IntegerScope scope = condition.getAges().get(index);
				buffer.append(scope.getMin()).append(" --- ").append(scope.getMax());
				if(index != condition.getAges().size() - 1) {
					buffer.append(", ");
				}
			}
			return buffer.toString();
		}
		return null;
	}

	private static void carView(UserProfileCondition condition,
			List<String> result) {
		List<CarCondition> cars = condition.getCars();
		if(!CollectionUtil.isEmpty(cars)) {
			boolean isNull = true;
			for(CarCondition car : cars) {
				if(car.getHave() != null) {
					isNull = false;
					break;
				}
			} 
			if(isNull) {
				return;
			}
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("是否有车").append(" : ");
			for(int index = 0; index < cars.size(); index++) {
				CarCondition car = cars.get(index);
				if(car.getHave() == null) {
					continue;
				}
				if(car.getHave().equals(Have.HAVE)) {
					buffer.append("有").append("   ");
					String carTypeStr = carTypeView(car);
					if(!StringUtil.isEmpty(carTypeStr)) {
						buffer.append(carTypeStr).append("   ");
					}
					String buyDateStr = buyDateView(car);
					if(!StringUtil.isEmpty(buyDateStr)) {
						buffer.append(buyDateStr).append("   ");
					}
				} else {
					buffer.append("否").append("   ");
				}
				if(index != cars.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static String buyDateView(CarCondition condition) {
		List<DateScope> scope = condition.getBuyDates();
		if(!CollectionUtil.isEmpty(scope)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("购买时间段").append(" --- ");
			for(int index = 0; index < scope.size(); index++) {
				String startDate = DateUtil.translateToString(scope.get(index).getStart(), DateUtil.YEAR_PATTERN);
				String endDate = DateUtil.translateToString(scope.get(index).getEnd(), DateUtil.YEAR_PATTERN);
				buffer.append(startDate).append(" --- ").append(endDate);
				if(index != scope.size() - 1) {
					buffer.append(", ");
				}
			}
			return buffer.toString();
		}
		return null;
	}
	
	private static String carTypeView(CarCondition condition) {
		List<CarType> cars = condition.getCarTypes();
		if(!CollectionUtil.isEmpty(cars)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("车型").append(" --- ");
			for(int index = 0; index < cars.size(); index++) {
				buffer.append(cars.get(index).getName());
				if(index != cars.size() - 1) {
					buffer.append(", ");
				}
			}
			return buffer.toString();
		}
		return null;
	}
	
	private static void dietTasteView(UserProfileCondition condition, List<String> result) {
		List<DietTaste> tastes = condition.getTastes();
		if(!CollectionUtil.isEmpty(tastes)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("饮食口味").append(" : ");
			for(int index = 0; index < tastes.size(); index++) {
				buffer.append(tastes.get(index).getName());
				if(index != tastes.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static void appHobbyView(UserProfileCondition condition, List<String> result) {
		List<PhoneAppHobby> appHobbies = condition.getAppHobbies();
		if(!CollectionUtil.isEmpty(appHobbies)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("手机应用喜好").append(" : ");
			for(int index = 0; index < appHobbies.size(); index++) {
				buffer.append(appHobbies.get(index).getName());
				if(index != appHobbies.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static void spareHobbyView(UserProfileCondition condition, List<String> result) {
		List<SpareHobby> spareHobbies = condition.getSpareHobbies();
		if(!CollectionUtil.isEmpty(spareHobbies)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("业余爱好").append(" : ");
			for(int index = 0; index < spareHobbies.size(); index++) {
				buffer.append(spareHobbies.get(index).getName());
				if(index != spareHobbies.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static void investmentView(UserProfileCondition condition, List<String> result) {
		List<Investment> investments = condition.getInvestments();
		if(!CollectionUtil.isEmpty(investments)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("投资意向").append(" : ");
			for(int index = 0; index < investments.size(); index++) {
				buffer.append(investments.get(index).getName());
				if(index != investments.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static void bodyView(UserProfileCondition condition, List<String> result) {
		List<BodyType> bodies = condition.getBodies();
		if(!CollectionUtil.isEmpty(bodies)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("体型").append(" : ");
			for(int index = 0; index < bodies.size(); index++) {
				buffer.append(bodies.get(index).getName());
				if(index != bodies.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static void familySalaryView(UserProfileCondition condition, List<String> result) {
		List<Salary> salaries = condition.getFamilySalaries();
		if(!CollectionUtil.isEmpty(salaries)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("家庭月收入").append(" : ");
			for(int index = 0; index < salaries.size(); index++) {
				buffer.append(salaries.get(index).getName());
				if(index != salaries.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static void marriageView(UserProfileCondition condition, List<String> result) {
		List<Marriage> marriages = condition.getMarriages();
		if(!CollectionUtil.isEmpty(marriages)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("婚烟").append(" : ");
			for(int index = 0; index < marriages.size(); index++) {
				buffer.append(marriages.get(index).getName());
				if(index != marriages.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static void degreeView(UserProfileCondition condition, List<String> result) {
		List<Degree> degrees = condition.getDegrees();
		if(!CollectionUtil.isEmpty(degrees)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("学历").append(" : ");
			for(int index = 0; index < degrees.size(); index++) {
				buffer.append(degrees.get(index).getName());
				if(index != degrees.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}

	private static void jobView(UserProfileCondition condition, List<String> result) {
		List<Job> jobs = condition.getJobs();
		if(!CollectionUtil.isEmpty(jobs)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("职业").append(" : ");
			for(int index = 0; index < jobs.size(); index++) {
				buffer.append(jobs.get(index).getName());
				if(index != jobs.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}

	private static void salaryView(UserProfileCondition condition, List<String> result) {
		List<Salary> salaries = condition.getSalaries();
		if(!CollectionUtil.isEmpty(salaries)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("月薪").append(" : ");
			for(int index = 0; index < salaries.size(); index++) {
				buffer.append(salaries.get(index).getName());
				if(index != salaries.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}

	private static void addressView(UserProfileCondition condition, List<String> result) {
		if(!CollectionUtil.isEmpty(condition.getAddresses())) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("地名").append(" : ");
			for(int index = 0; index < condition.getAddresses().size(); index++) {
				buffer.append(condition.getAddresses().get(index));
				if(index != condition.getAddresses().size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}

	private static void areaView(UserProfileCondition condition, List<String> result) {
		List<AreaCondition> areas = condition.getAreas();
		if(!CollectionUtil.isEmpty(areas)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("地区").append(" : ");
			for(int index = 0; index < areas.size(); index++) {
				AreaCondition area = areas.get(index);
				//因为在保存UserProfile时没把整个Area对象(包括其City和Province)保存,故暂时city和province为null不显示
//				buffer.append(area.getCity().getProvince().getName());
//				buffer.append(area.getCity().getName());
				buffer.append(area.getName());
				if(index != areas.size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}

	private static void ageView(UserProfileCondition condition, List<String> result) {
		if(!CollectionUtil.isEmpty(condition.getAges())) {
			boolean isNull = true;
			for(IntegerScope scope : condition.getAges()) {
				if(scope.getMin() != null || scope.getMax() != null) {
					isNull = false;
					break;
				}
			}
			if(isNull) {
				return;
			}
			StringBuffer buffer = new StringBuffer();
			buffer.append("年龄段").append(" : ");
			for(int index = 0; index < condition.getAges().size(); index++) {
				buffer.append(condition.getAges().get(index).getMin()).append(" --- ").append(condition.getAges().get(index).getMax());
				if(index != condition.getAges().size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}

	private static void genderView(UserProfileCondition condition,
			List<String> result) {
		if(!CollectionUtil.isEmpty(condition.getGenders())) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("性别").append(" : ");
			for(int index = 0; index < condition.getGenders().size(); index++) {
				buffer.append(condition.getGenders().get(index).getName());
				if(index != condition.getGenders().size() - 1) {
					buffer.append(", ");
				}
			}
			result.add(buffer.toString());
		}
	}
	
	private static String[] convert(List<String> result) {
		String[] resultArray = new String[result.size()];
		for(int index = 0; index < result.size(); index++) {
			resultArray[index] = result.get(index);
		}
		return resultArray;
	}
}
