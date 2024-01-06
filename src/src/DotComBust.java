package src;

import java.util.ArrayList;

public class DotComBust {

	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;

	protected void setUpGame() {
		DotCom one = new DotCom();
		one.setName("GIT.com");
		DotCom two = new DotCom();
		two.setName("OZON.com");
		DotCom three = new DotCom();
		three.setName("VK.com");
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);

		System.out.println("Ваша цель - потопить три сайта.");
		System.out.println("GIT.com, OZON.com, VK.com");
		System.out.println("Попытайтесь их потопить за минимальное количество ходов");

		for (DotCom dotComSet : dotComsList) {
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComSet.setLocationCells(newLocation);
		}
	}

	protected void startPlaying() {
		while (!dotComsList.isEmpty()) {
			String userGuess = helper.getUserInput("Сделайте ход");
			checkUserGuess(userGuess);
		}
		finishGame();
	}

	private void checkUserGuess(String userGuess) {
		numOfGuesses++;
		String result = "Мимо";

		for (DotCom dotComToTest : dotComsList) {
			result = dotComToTest.checkYourself(userGuess);
			if (result.equals("Попал")) {
				break;
			}
			if (result.equals("Потопил")) {
				dotComsList.remove(dotComToTest);
				break;
			}
		}
		System.out.println(result);
	}

	private void finishGame() {
		System.out.println("Все сайты ушли ко дну! Ваши акции тепрь ничего не стоят.");
		if (numOfGuesses <= 18) {
			System.out.println("Это заняло у вас всего " + numOfGuesses + " попыток.");
			System.out.println("Вы успели выбраться до того, как ваши вложения утонули.");
		} else {
			System.out.println("Это заняло у вас довольно много времени. " + numOfGuesses + " попыток.");
			System.out.println("Рыбы водят хороводы вокруг ваших вложений.");
		}
	}

}
