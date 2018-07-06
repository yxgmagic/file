package com.zhichao.service.flowable;

import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Expense;
import com.zhichao.beans.vo.TaskVo;

/**
 * <p>
 * 报销表 服务类
 * </p>
 *
 * @author zhichao
 * @since 2017-12-04
 */
public interface IExpenseService extends IService<Expense> {

    /**
     * 新增一个报销单
     */
    void add(Expense expense);

    /**
     * 删除一个报销单
     */
    void delete(Integer expenseId);

    /**
     * 通过审批
     */
    void pass(String taskId);

    /**
     * 通过审批
     */
    void unPass(String taskId);

    /**
     * 获取审批列表
     */
    List<TaskVo> getProcessList();

    /**
     * 绘画当前流程图
     */
    void printProcessImage(Integer expenseId) throws IOException;

}
