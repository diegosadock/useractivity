package useractivity;

import java.util.Comparator;
import java.util.List;

import useractivity.formatter.GithubActivityFormatter;
import useractivity.model.UserActivity;
import useractivity.services.UserActivityServiceImpl;

public class Main {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Uso: java Main <GitHub-username>");
			return;
		}

		String username = args[0];

		UserActivityServiceImpl service = new UserActivityServiceImpl();

		List<UserActivity> activities = service.fetchUserActivities(username);

		activities.stream().sorted(Comparator.comparing(UserActivity::getCreated_at).reversed())
				.map(activity -> GithubActivityFormatter.format(activity)).forEach(formattedActivity -> {
					formattedActivity.ifPresent(System.out::println);
				});

	}

}
