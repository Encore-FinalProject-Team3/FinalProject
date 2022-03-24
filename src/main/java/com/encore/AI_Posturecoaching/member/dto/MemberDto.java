package com.encore.AI_Posturecoaching.member.dto;


import com.encore.AI_Posturecoaching.member.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String memberName;
    private String role;

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getMemberName(),member.getRole());
    }

    public static MemberDto empty() {
        return new MemberDto(null, "", "","");
    }


}
