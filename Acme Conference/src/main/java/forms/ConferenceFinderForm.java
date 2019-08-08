
package forms;

public class ConferenceFinderForm {

	private String	keyWord;


	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	public ConferenceFinderForm create() {
		final ConferenceFinderForm res = new ConferenceFinderForm();

		res.setKeyWord("");
		return res;
	}

}
