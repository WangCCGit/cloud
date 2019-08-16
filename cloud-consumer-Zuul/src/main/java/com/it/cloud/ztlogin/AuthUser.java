package com.it.cloud.ztlogin;

import lombok.Builder;
import lombok.Data;
import com.it.cloud.ztfilter.TbUser;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
public class AuthUser implements Serializable {

    private static final long serialVersionUID = -4199550203850153635L;

    private Long id;
    private String aliasName ;
    private String mobile ;
    private String email ;
    private String verifiedStatus ;
    private String realName ;
    private String gender ;
    private String idcardNo;
    private String idcardPicPath;
    private String avatarPicPath;
    private Date availableTime;
    private String recommendCode;
    private String recommendId;
    private String userIdentity;
    private String recommStatus;
    private TbUserAccount tbUserAccount;
    private String groupLevel;
    private String userCenter;



	public static AuthUser toAuthUser(TbUser user) {
        return AuthUser.builder()
                .id(user.getId())
                .aliasName(user.getAliasName())
                .mobile(user.getMobile())
                .email(user.getEmail())
                .realName(user.getRealName())
                .gender(user.getGender())
                .verifiedStatus(user.getVerifiedStatus())
                .idcardPicPath(user.getIdcardPicPath())
                .avatarPicPath(user.getAvatarPicPath())
                .availableTime(user.getAvailableTime())
                .idcardNo(user.getIdcardNo())
                .recommendCode(user.getRecommendCode())
                .recommendId(user.getRecommendId())
                .userIdentity(user.getUserIdentity())
                .recommStatus(user.getRecommStatus())
                .tbUserAccount(user.getTbUserAccount())
                .groupLevel(user.getGroupLevel())
                .userCenter(user.getUserCenter())
                .build();

    }
}
