package sample.firebase;


public class FirebaseConfig {

	private String URL;
	private String apikey;

	public FirebaseConfig() {
	}

	public FirebaseConfig(String URL, String apikey) {
		this.URL = URL;
		this.apikey = apikey;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getURL() {
		return URL;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}


}
