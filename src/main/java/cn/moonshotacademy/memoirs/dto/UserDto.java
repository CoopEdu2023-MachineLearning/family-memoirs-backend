package cn.moonshotacademy.memoirs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    private String username;

    private String email;

    private String password;

    private String photoAddress;

    private String introduction;

    private String name;

}
