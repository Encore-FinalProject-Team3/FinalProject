package com.encore.AI_Posturecoaching.comment.event;

import com.encore.AI_Posturecoaching.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentCreatedEvent {
    private MemberDto publisher;
    private MemberDto postWriter;
    private MemberDto parentWriter;
    private String content;
}