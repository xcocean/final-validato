package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import param.AddProductParam;
import param.NestingParam1;
import param.NestingParam2;
import param.ProductAttrParam;
import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * 嵌套测试
 * @author lingkang
 * Create by 2024/12/22 0:28
 */
public class Test03Nesting {
    @BeforeEach
    public void init() {
        FinalValidator.init();
    }

    @Test
    public void test01(){
        AddProductParam param=new AddProductParam();
        param.setPrice(1);
        param.setName("三折叠");
        ProductAttrParam attrParam=new ProductAttrParam();
        attrParam.setType("aa");
        attrParam.setSize("small");
        // attrParam.setProduct(new AddProductParam().setName("三折叠2"));
        NestingParam1 param1=new NestingParam1();
        attrParam.setNestingParam1(param1);
        param1.setNesting1("n1");
        param1.setNestingParam2(new NestingParam2().setNesting2("n2"));
        // param1.getNestingParam2().setNesting3("n3");
        param.setAttr(attrParam);
        try {
            FinalValidator.valid(param);
        }catch (Exception e){
            throw e;
        }
    }
}
