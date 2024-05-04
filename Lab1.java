
public class Lab1 {
    /*
        * Startar kattis i/o
        * Skriver ut längsta substrings längd
        */
   public static void main(String[] args) {
      
       Kattio io = new Kattio(System.in, System.out);
       String string = io.getWord();
       int length = findLongestLength(string);
       io.println(length);
       io.close();
   }
   /*
    * findLongestLength(String string) 
    * input: En sträng
    * räknar längden på alla substrings som är sorterade
    * 
    * 
    * return: hur lång den längsta delsträngen är, det högsta värdet i listan
    * 
    */
            public static int findLongestLength(String string) {
      if (string.isEmpty()) return 0; 
      char[] stringArr = string.toCharArray();

      int TempLength = 1;
      int Length = 1; 

      for (int i = 1; i < string.length(); i++) {
          if (stringArr[i] >= stringArr[i - 1]) {
              TempLength++; 
          } else {
              if(Length>TempLength){
              TempLength = 1; 
          }else{Length = TempLength;
               TempLength = 1; 

          }
      }}
      
      Length = Math.max(Length, TempLength); 

      return Length;
  }
}