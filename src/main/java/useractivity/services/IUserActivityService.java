package useractivity.services;

import java.util.List;

import useractivity.model.UserActivity;

public interface IUserActivityService {
	public List<UserActivity> fetchUserActivities(String username);

}
