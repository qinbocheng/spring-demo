package generator.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import generator.domain.box;
import generator.service.boxService;
import generator.mapper.boxMapper;
import org.springframework.stereotype.Service;

/**
* @author qing
* @description 针对表【box(盲盒表)】的数据库操作Service实现
* @createDate 2022-05-08 22:54:59
*/
@Service
public class boxServiceImpl extends ServiceImpl<boxMapper, box>
implements boxService{

}
