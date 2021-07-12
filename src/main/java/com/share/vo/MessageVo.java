package com.share.vo;

import com.share.entity.Material;
import com.share.entity.Message;
import com.share.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: MessageVo
 * @Description: TODO
 * @author: 莫提
 * @date 2020/11/14 17:26
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageVo {
    private Message message;
    private User sender;
    private Material material;
}
