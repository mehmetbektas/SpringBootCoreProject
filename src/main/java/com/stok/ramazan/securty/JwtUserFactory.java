package com.stok.ramazan.securty;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.stok.ramazan.entity.Role;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil.EntityState;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
    	boolean aktifPasif=false;
    	if(user.getEntityState()==EntityState.ACTIVE){
    		aktifPasif=true;
    	}
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getAdi(),
                user.getSoyadi(),
                user.getUserName(),
                user.getPassword(),
                mapToGrantedAuthorities(Arrays.asList( user.getRole())),
                aktifPasif,
                user.getCreatedDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getYetkiAdi()))
                .collect(Collectors.toList());
    }
}
