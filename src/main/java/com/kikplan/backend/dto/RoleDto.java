package com.kikplan.backend.dto;


import com.kikplan.backend.entities.Role;
import com.kikplan.backend.entities.RoleType;
import com.kikplan.backend.entities.User;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private Long userId;
    private RoleType roleType;


    public RoleDto fromEntity(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .roleType(role.getType())
                .userId(role.getUser().getId())
                .build();
    }

    public Role toEntity(RoleDto roleDto){
        return Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .type(roleDto.getRoleType())
                .user(User.builder()
                        .id(roleDto.getUserId())
                        .build())
                .build();
    }

}
