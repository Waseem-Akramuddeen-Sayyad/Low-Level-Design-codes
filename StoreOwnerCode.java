class Box{                                     
         
          int length;
          int breadth;
          int days;
          int rent;

          public Box(int length, int breadth, int days, int rent){
               
                this.length = length;
                this.breadth  = breadth;
                this.days = days;
                this.rent = rent;
          }


                void displayBox(){
                       System.out.println(this.length+""+this.breadth+""+this.days+""+this.rent);     
                }
                
}


 class BoxStorage{
     
       private int storageLength;
       private int storageBreadth;
       private int totalrentcollected = 0;
       private int thresholdrent =0;
       private int storage[][];



        public BoxStorage(int length, int breadth, List<Box>boxesData){
                this.storageLength = length;
                this.storageBreadth = breadth;
                this.totalrentcollected = 0;
                this.thresholdrent = calculatethresholdrent(boxesData);
                System.out.println(thresholdrent);
                storage = new int[length][breadth];


        }


         private int calculatethresholdrent(List<Box>boxesData){

             List<Integer>rent = new ArrayList();

              for(Box B : boxesdata){
                    int totaldata = B.length * B.breadth * B.days;
                    int rentperunit =  B.rent/totaldata;
              }


                 rent.add(rentperunit);    

                 Collections.sort(rent);

                  int n = rent.size();

                  if(n%2==0){                 //  fixing threshold median according to the rent 
                        
                     return (rent.get(n/2-1) + rent.get(n/2));
                  }else{
                        
                      return (rent.get(n/2));
                  }




                     public int getrent(){
                           return totalrentcollected;
                     }
             
         }


           public boolean isvalidbox(Box B , int currentday){
              
                  int rentperunit = B.rent/B.length*B.breadth*B.days;


                   if(rentperunit<=this.thresholdrent){
                        return false;

                   }


                     int topleftx = -1;
                     int toplefty =  -1;
                     boolean isvalid = false;


                      for(int i=0; i<topleftx+length; i++){
                             for(int j=0; j<toplefty+breadth; j++){
                                  if(isvalidtopleft(i, j, B.length, B.breadth, currentday)){
                                        topleftx = i;
                                        toplefty = i;
                                        isvalid = true;
                                        break;
                                  }
                             }

                              if(isvalid) break;
                      }



                        if(isvalid){
                              totalrentcollected+=B.rent;        // collecting rent 

                               for(int i=topleftx; i<topleftx+B.length; i++){
                                 for(int j=toplefty; j<toplefty+B.breadth; j++){

                                      storage[i][j] = currentday+B.days-1;
                                     
                                 }
                               }

                                return true;
                      }


                         return false;


           }

                    public boolean isvalidtopleft(int topleftx, int toplefty, int length, int breadth, int currentday){
                          for(int i=topleftx; i<topleftx; i++){
                               for(int j=toplefty; j<toplefty; j++){
                                        if(storage[i][j]>=currentday){   // if its already occupied 
                                             return false;
                                        }
                               }
                          }


                           return true;
                           
                    }
                
}

public class Main {
     public static void main(String[] args){
         
          int n = 70; // storage length
          int m = 40;  // storage breadth

           
          int[][] b1 = {{6, 8, 3, 1500}, {7, 4, 2, 1600}, {8, 5, 4, 1700}, {10, 8, 2, 1450}, {15, 20, 6, 1400}};      // the historical boxes data which already present

          int[][] b2 =  {{4, 3, 1, 2500}, {3, 4, 2, 2300}, {5, 4, 3, 2200}, {6, 5, 4, 3500}, {16, 22, 5, 2100}};            // the incoming boxes queries




          // add it into list of boxes data to calculate the rent 
               List<Box>boxesData = new ArrayList();
               List<Box>incomingboxQueries = new ArrayList();

           for(int[]b : b1){
                 boxesData.add(b[0], b[1], b[2], b[3]);

                
                
           }


            for(int[]b : b2){
                      incomingboxQueries.add(b[0], b[1], b[2], b[3]);
            }




               
                       // here we are sending the boxesdata for calculation 

                        BoxStorage bs = new BoxStorage(boxesData);

                         int currentday = 1;



                        for(Box query : incomingboxQueries){
                             boolean validbox  = bs.isvalidBox(query, currentday);

                                 System.out.println(validbox+""+bs.getrent());
                                 currentday++;
                        }


           

           
     }
    
}
