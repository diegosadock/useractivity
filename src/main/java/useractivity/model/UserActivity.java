package useractivity.model;

import java.time.OffsetDateTime;

public class UserActivity {
	private String type;
	private Repo repo;
	private Payload payload;
	private OffsetDateTime created_at;

	public UserActivity() {
		super();
	}

	public UserActivity(String type, Repo repo, Payload payload, OffsetDateTime created_at) {
		super();
		this.type = type;
		this.repo = repo;
		this.payload = payload;
		this.created_at = OffsetDateTime.now();
	}

	public OffsetDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(OffsetDateTime created_at) {
		this.created_at = created_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Repo getRepo() {
		return repo;
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

}
