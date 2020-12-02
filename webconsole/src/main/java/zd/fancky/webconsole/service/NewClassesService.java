package zd.fancky.webconsole.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zd.fancky.webconsole.dao.newClasses.FeedBackMapper;
import zd.fancky.webconsole.model.entity.newClasses.FeedBack;
import zd.fancky.webconsole.model.viewmodel.TPSQueueVM;
import zd.fancky.webconsole.model.viewmodel.TPSVM;
import zd.fancky.webconsole.model.vo.MessageResult;
import zd.fancky.webconsole.model.vo.PageData;

import java.util.List;

@Service
public class NewClassesService {
    Logger logger = LoggerFactory.getLogger(TPSService.class);
    @Autowired
    FeedBackMapper feedBackMapper;
    public MessageResult<Void> insert(FeedBack model) {
        MessageResult<Void> messageResult = new MessageResult<>();
        try {
            Integer result = feedBackMapper.insert(model);
            messageResult.setSuccess(result > 0);
        } catch (Exception ex) {
            messageResult.setMessage(ex.getMessage());
            messageResult.setSuccess(false);
            logger.error(ex.toString());
        } finally {
            return messageResult;
        }
    }

//    public MessageResult<PageData<AuthoritiesVM>> getPageDataWithCount(AuthoritiesVM viewModel) {
//        MessageResult<PageData<AuthoritiesVM>> message = new MessageResult<>();
//        try {
//            PageData<AuthoritiesVM> paegData = new PageData<>();
//            Integer count = authoritiesMapper.getPageDataCount(viewModel);
//            paegData.setCount(count);
//            List<AuthoritiesVM> list = authoritiesMapper.getPageData(viewModel);
//            paegData.setData(list);
//            message.setData(paegData);
//            message.setSuccess(true);
//        } catch (Exception ex) {
//            message.setSuccess(false);
//            message.setMessage(ex.getMessage());
//            logger.error(ex.toString());
//        } finally {
//            return message;
//        }
//    }
}
