package com.comsultant.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long idx;
    private String nickName;
    private Long accountIdx;
    private Long productIdx;
    private String content;
    private String createDate;

    public void updateUserInfo(long accountIdx, long productIdx) {
        this.accountIdx = accountIdx;
        this.productIdx = productIdx;
    }

    public void updateNickName(String nickName) {
        this.nickName = nickName;
    }
}
