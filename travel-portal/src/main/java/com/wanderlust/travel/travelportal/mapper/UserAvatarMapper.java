package com.wanderlust.travel.travelportal.mapper;

import com.wanderlust.travel.travelportal.entity.UserAvatar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 * 用户头像表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface UserAvatarMapper extends BaseMapper<UserAvatar> {

    /**
     * 将用户的所有头像设为非当前
     */
    @Update("UPDATE user_avatar SET is_current = 0 WHERE user_id = #{userId}")
    int setAllAvatarsInactive(@Param("userId") Long userId);

    /**
     * 删除用户的所有头像记录
     */
    @Delete("DELETE FROM user_avatar WHERE user_id = #{userId}")
    int deleteAllUserAvatars(@Param("userId") Long userId);
}