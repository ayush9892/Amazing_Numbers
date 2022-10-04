package numbers;

public class Main {

    public static void main(String[] args) {
        NoProperty numPop = new NoProperty();
        EntryDisplay Entry = new EntryDisplay();
        UserInput userInp = new UserInput(numPop);

        userInp.TakingInput(Entry);
    }
}
