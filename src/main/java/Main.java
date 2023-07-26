public class Main {
    public static void main(String[] args) {
        Requests requests = new Requests();
        String s1 = requests.saveUser(3L);
        String s2 = requests.updateUser(3L);
        String s3 = requests.deleteUser(3L);
        String string = s1 + s2 + s3;
        if(string.length()==18){
            System.out.println("Ответ: "+"\n"+string);
        }else {
            System.out.println("Допущена оплошность");
        }
    }
}
