package useractivity.formatter;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import useractivity.model.UserActivity;

public class GithubActivityFormatter {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static Optional<String> format(UserActivity event) {

        String repo = event.getRepo().name();
        String date = event.getCreated_at()
                .format(DATE_FORMATTER);

        return switch (event.getType()) {

            case "WatchEvent" -> {
                if ("started".equals(event.getPayload().action())) {
                    yield Optional.of("[" + date + "] Starred " + repo);
                }
                yield Optional.empty();
            }

            case "PushEvent" -> {
                String branch = event.getPayload().ref();

                if (branch != null && branch.startsWith("refs/heads/")) {
                    branch = branch.replace("refs/heads/", "");
                }

                yield Optional.of(
                        "[" + date + "] Pushed to " + repo +
                        (branch != null ? " (" + branch + ")" : "")
                );
            }

            case "CreateEvent" -> {
                String refType = event.getPayload().ref_type();
                String ref = event.getPayload().ref();

                if ("branch".equals(refType)) {
                    yield Optional.of(
                            "[" + date + "] Created branch " + ref + " in " + repo
                    );
                }

                yield Optional.of(
                        "[" + date + "] Created " + refType + " in " + repo
                );
            }

            case "IssuesEvent" -> {
                String action = event.getPayload().action();

                if ("opened".equals(action)) {
                    yield Optional.of(
                            "[" + date + "] Opened a new issue in " + repo
                    );
                }

                if ("closed".equals(action)) {
                    yield Optional.of(
                            "[" + date + "] Closed an issue in " + repo
                    );
                }

                yield Optional.empty();
            }

            case "PullRequestEvent" -> {
                if ("opened".equals(event.getPayload().action())) {
                    yield Optional.of(
                            "[" + date + "] Opened a pull request in " + repo
                    );
                }
                yield Optional.empty();
            }

            case "ForkEvent" ->
                Optional.of("[" + date + "] Forked " + repo);

            default -> Optional.empty();
        };
    }
}
