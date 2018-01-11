package com.wusc.campaign.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wusc.campaign.model.Channel;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wusichao
 * @since 2017-12-22
 */
public interface ChannelMapper extends BaseMapper<Channel> {

    List<Channel> getChannelByIds(String channelIds);
}
