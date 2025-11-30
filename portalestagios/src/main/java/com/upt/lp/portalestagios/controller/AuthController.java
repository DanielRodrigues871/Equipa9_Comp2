package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.service.UtilizadorService;
import com.upt.lp.portalestagios.util.SessaoUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UtilizadorService userService;

    public AuthController(UtilizadorService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        Utilizador u = userService.autenticar(email, password);

        if (u == null)
            return "Credenciais inválidas";

        SessaoUtil.setUtilizadorLogado(u);
        return "Login efetuado com sucesso!";
    }

    @PostMapping("/logout")
    public String logout() {
        SessaoUtil.setUtilizadorLogado(null);
        return "Sessão terminada.";
    }
}
