package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;
    public void add(Label label) {
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public void delete(String labelId) {

        labelDao.deleteById(labelId);
    }

    public void update(Label label) {

        labelDao.save(label);
    }

    public Label findById(String labelId) {
        return labelDao.findById(labelId).get();
    }

    public List<Label> findSeach(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，要把条件封装到哪个条件中 where 类名=label.geid
             * @param query 封装的都是查询关键字，比如group by order by
             * @param cb 用来封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new List集合存放所有的条件
                List<Predicate> list =new ArrayList<>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");// labelname like %xx%
                    list.add(predicate);
                }
                if(label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("labelname").as(String.class),  label.getState() );// getState = 1
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转换为数组
                parr = list.toArray(parr);
                return cb.and(parr);//where labelname like "%xx%" and state=1
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable=PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象，要把条件封装到哪个条件中 where 类名=label.geid
             * @param query 封装的都是查询关键字，比如group by order by
             * @param cb 用来封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new List集合存放所有的条件
                List<Predicate> list =new ArrayList<>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");// labelname like %xx%
                    list.add(predicate);
                }
                if(label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("labelname").as(String.class),  label.getState() );// getState = 1
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转换为数组
                parr = list.toArray(parr);
                return cb.and(parr);//where labelname like "%xx%" and state=1
            }
        }, pageable);

    }
}
