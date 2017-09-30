package bo;

import dao.KeyDAO;
import dao.UserDAO;
import java.util.ArrayList;
import model.Key;
import model.User;

public class UserBO {

    public static final String BASE_URL = "http://localhost:8080/CNW_GiaPha";
    // viết tất cả các hàm đc sửa dụng tại đây
    UserDAO _DAO;
    KeyDAO _KEY;

    public UserBO() {
        _DAO = new UserDAO();
        _KEY = new KeyDAO();
    }

    public User checkLogin(String user, String pass) {
        User item = _DAO.getItem(user, pass);
        if (item != null && item.getRoles() > 0) {
            return item;
        }
        return null;
    }

    public ArrayList<User> getList(long start, long num, String key) {
        return _DAO.getList(start, num, key);
    }

    public long countItems(String key) {
        return _DAO.countItems(key);
    }

    public User checkEmail(String key) {
        return _DAO.getItemByEmail(key);
    }

    public User checkUser(String key) {
        return _DAO.getItemByUser(key);
    }

    public User checkEmailUser(String key) {
        return _DAO.getItem(key);
    }

    public int addItem(User item) {
        //add key
//        String key = ScratchSpace.createLicenseKey(item.getUsername(), item.getEmail(), "create");
//        _KEY.addItem(new Key(System.currentTimeMillis(), item.getId(), key));
//        // send email
//        sendEmailActive(item.getEmail(), key);
        return _DAO.addItem(item);
    }

    /*private void sendEmailActive(String email, String key) {
        String subject = "[GIA PHẢ] ACTIVE";
        String body = "<h1>Gia Phả xin chào</h1>\n"
                + "please click here to active: <a href='" + BASE_URL + "/User?command=active&key=" + key + "'>link here</a>\n"
                + "please click here to view pass: <a href='" + BASE_URL + "/User?command=showpass&key=" + key + "'>link here</a>\n";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SendEmail.sendFromGMail(email, subject, body);
            }
        });
        thread.start();
    }*/

    public int activeItem(String key) {
        // get id by key
        Key itemKey = _KEY.getByKey(key);
        if (itemKey == null) {
            return 0;
        }
        User itemUser = _DAO.getItem(itemKey.getId_user());
        itemUser.setRoles(1);
        int result = _DAO.editItem(itemUser);
        _KEY.deleteItem(itemKey);
        return result;
    }

    public int reactiveItem(String email) {
        User item = _DAO.getItemByEmail(email);
        if (item == null) {
            return 0;
        }
        Key itemKey = _KEY.getItem(item.getId());
        if (itemKey == null) {
            //add key mới
            addItem(item);
            itemKey = _KEY.getItem(item.getId());
        }
        //sendEmailActive(item.getEmail(), itemKey.getKey());
        return 1;
    }

    public String viewPassByKey(String key){
         // get id by key
        Key itemKey = _KEY.getByKey(key);
        if (itemKey == null) {
            return "";
        }
        User itemUser = _DAO.getItem(itemKey.getId_user());
        _KEY.deleteItem(itemKey);
        
        return itemUser!=null?itemUser.getPass():"";
    }
    public static void main(String[] args) {
        //System.out.println(new UserBO().addItem(new User(System.currentTimeMillis(), "u1", "e1", "p", 0)));
        //check login
        System.out.println(new UserBO().checkLogin("lemin2601@gmail.com","trungdinh1").toString());
        System.out.println(new UserBO().viewPassByKey("1D9F69-FE3484-B7712A-300DB9-D54081-B50B44-6780"));
//        if (new UserBO().activeItem("1D9F69-FE3484-B7712A-300DB9-D54081-B50B44-6780") > 0) {
//            System.out.println("Account was actived. <a href='index.jsp'>Login here</a>");
//        } else {
//            System.out.println("You key not correct, try again.");
//        }
        
    }
}
