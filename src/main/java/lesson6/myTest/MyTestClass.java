package lesson6.myTest;

public class MyTestClass {

    public int[] scissor(int[] input){

        if(input == null){
            System.err.println("Вы передали пустой массив");
            return null;
        }

//        try {
            int lastPosition = 0;

            for (int i = 0; i < input.length; i++) {
                if(input[i] == 4) lastPosition = i;
            }
            if (lastPosition == 0) throw new RuntimeException();
            int newLength = input.length - lastPosition - 1;
            int[] output = new int[newLength];
            System.arraycopy(input,lastPosition + 1,output, 0, newLength);
            return output;
//        } catch (RuntimeException e) {
////            System.err.println("Вы передали массив, в котором нет цифры '4'!");
//            e.getMessage();
//        }
//        return null;
    }

    public boolean have1or4(int[] mass){
        for (int i : mass) {
            if (i == 1 || i == 4) return true;
        }
        return false;
    }

}
