package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.Utilisateur;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsDemo implements UserDetails {

        private Utilisateur utilisateur;
    public UserDetailsDemo(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();
        Hibernate.initialize((utilisateur.getListeRole()));
        for (Role role : this.utilisateur.getListeRole()){
            listeAuthority.add(new SimpleGrantedAuthority(role.getNom()));
        }



        return listeAuthority;
    }

    @Override
    public String getPassword() {
        return utilisateur.getMdp();
    }

    @Override
    public String getUsername() {
        return utilisateur.getNom();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
