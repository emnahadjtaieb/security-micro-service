package com.example.demo.web;

import com.example.demo.common.email.EmailService;
import com.example.demo.common.utils.ResponseTransfer;
import com.example.demo.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseTransfer register(@RequestBody UserForm userForm) {

        if (userForm.getEmail() == null) return new ResponseTransfer("please enter email");

        if (userForm.getUsername() == null) return new ResponseTransfer("please enter username");

        if (userForm.getPassword() == null) return new ResponseTransfer("please enter password");

        if (userForm.getConfirmedPassword() == null)
            return new ResponseTransfer("please enter password confirmation");
        else if (!userForm.getPassword().equals(userForm.getConfirmedPassword()))
            return new ResponseTransfer("please cofirm password");

        accountService.saveUser(userForm.getUsername(), userForm.getPassword());
        emailService.sendSimpleMessage(userForm.getEmail(),
                "Votre compte",
                "Bonjour,\n" +
                        "Votre nom d'utilisateur et mot de passe sur notre application sont :\n" +
                        "Nom d'utilisateur : " + userForm.getUsername() + "\n" +
                        "Mot de passe : " + userForm.getPassword());

        return new ResponseTransfer("compte created with succès");
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseTransfer updatePassword(@RequestBody UserForm userForm) {

        if (userForm.getUsername() == null) return new ResponseTransfer("please enter username");

        if (userForm.getPassword() == null) return new ResponseTransfer("please enter password");

        if (userForm.getConfirmedPassword() == null)
            return new ResponseTransfer("please enter password confirmation");
        else if (!userForm.getPassword().equals(userForm.getConfirmedPassword()))
            return new ResponseTransfer("please confirm password");

        accountService.updatePassword(userForm.getUsername(), userForm.getPassword());

        return new ResponseTransfer("password modified with succès");
    }
}
@Data
class UserForm{
    private String email;
    private String username;
    private String password;
    private String confirmedPassword;

}
