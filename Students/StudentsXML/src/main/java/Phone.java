public class Phone {
    private String mobile;

    public Phone(){
        mobile = "";
    }
    public Phone(String number){
        mobile = number;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return mobile;
    }
}
