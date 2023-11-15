package christmas.Menu;

// enum 인터페이스?

public enum Appetizer {
    APPETIZER_SALAD("시저샐러드", 8_000, 1),
    APPETIZER_SOUP("양송이수프", 6_000, 2),
    APPETIZER_TAPAS("타파스", 5_500, 3);
    private final String foodName;
    private final int foodPrice;
    private final int foodIndex;

    public String getAppetizerName() {
        return foodName ;
    }
    public int getAppetizerPrice() {
        return foodPrice ;
    }
    public int getAppetizerIndex() {
        return foodIndex ;
    }



    Appetizer(String foodName, int foodPrice, int foodIndex) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodIndex = foodIndex;
    }

//    public static LottoRank getMatchedLottoRank(AnswerLotto answerLotto, Lotto lotto) {
//        int matchedNumberCount = countMatchedNumber(answerLotto, lotto);
//        boolean isBonusNumber = isNumberSameAsBonusNumber(answerLotto, lotto);
//
//        return LottoRank.getMatchedLottoRank(matchedNumberCount, isBonusNumber);
//    }
//
//    private static LottoRank getMatchedLottoRank(int matchedNumberCount, boolean isBonusNumber) {
//        return Arrays.stream(LottoRank.values())
//                .filter(lottoRank -> lottoRank.matchingCondition.test(matchedNumberCount, isBonusNumber))
//                .findAny()
//                .orElse(NOTHING);
//    }
//
//    private static int countMatchedNumber(AnswerLotto answerLotto, Lotto lotto) {
//        int matchedNumberCount = 0;
//
//        for (int index = 0; index < lotto.getSize(); index++) {
//            if (answerLotto.isContain(lotto.getNumber(index))) {
//                matchedNumberCount++;
//            }
//        }
//
//        return matchedNumberCount;
//    }
//
//    private static boolean isNumberSameAsBonusNumber(AnswerLotto answerLotto, Lotto lotto) {
//        boolean isBonusNumber = false;
//
//        for (int index = 0; index < lotto.getSize(); index++) {
//            if (answerLotto.getBonusNumber() == lotto.getNumber(index)) {
//                isBonusNumber = true;
//                break;
//            }
//        }
//
//        return isBonusNumber;
//    }
//
//    public static EnumMap<LottoRank, Integer> initializeLottoRankCounter() {
//        EnumMap<LottoRank, Integer> lottRankCounter = new EnumMap<>(LottoRank.class);
//        Arrays.stream(LottoRank.values()).forEach(lottoRank -> lottRankCounter.put(lottoRank, 0));
//        return lottRankCounter;
//    }
//
//    public int getLottoPrice() {
//        return lottoPrice;
//    }
//
//    public int getMatchedNumCount() {
//        return matchedNumCount;
//    }
//
//    public boolean getisBonusNumber() {
//        return isBonusNumber;
//    }

}

