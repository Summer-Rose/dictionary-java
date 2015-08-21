import java.util.ArrayList;

public class Word {
	private static ArrayList<Word> instances = new ArrayList<Word>();
	private String mWord;
	private int mId;

	public Word(String word) {
		mWord = word;
	}

	public String getWord() {
		return mWord;
	}

	public int getId() {
		return mId;
	}

	public static ArrayList<Word> all() {
		return instances;
	}
}
