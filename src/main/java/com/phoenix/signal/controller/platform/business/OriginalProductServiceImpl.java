package com.phoenix.signal.controller.platform.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phoenix.signal.controller.platform.dto.request.ProductRequest;
import com.phoenix.signal.controller.platform.exception.ConflictException;
import com.phoenix.signal.controller.platform.exception.NotFoundException;
import com.phoenix.signal.controller.platform.model.OriginalProduct;
import com.phoenix.signal.controller.platform.service.OriginalProductDbService;
import com.phoenix.signal.controller.platform.service.OriginalProductDbServiceImpl;
import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.util.List;

@Service
@AllArgsConstructor
public class OriginalProductServiceImpl implements OriginalProductService{

    private OriginalProductDbService originalProductDbService;
    private ModelMapper modelMapper;

    @Override
    public List<OriginalProduct> getAllOriginalProduct() {
        return originalProductDbService.getAll();
    }

    @Override
    public String create(ProductRequest productRequest) {
        checkDuplicatedProduct(productRequest.getProductId());
        OriginalProduct originalProduct = modelMapper.map(productRequest, OriginalProduct.class);
        if(originalProductDbService.save(originalProduct)){
            return productRequest.getProductName() + "添加成功";
        }

        return productRequest.getProductName() + "添加失败";
    }

    @Override
    public IPage<OriginalProduct> page(Page<OriginalProduct> page) {
        return originalProductDbService.page(page);
    }

    @Override
    public String update(Long productId, ProductRequest productRequest) {

        OriginalProduct originalProduct = originalProductDbService.getById(productId);
        if(originalProduct == null) throw new NotFoundException(ExceptionEnum.NOT_FOUND);

        originalProduct.setProductId(productRequest.getProductId());
        originalProduct.setProductName(productRequest.getProductName());
        originalProduct.setProductBrand(productRequest.getProductBrand());
        originalProduct.setProductType(productRequest.getProductType());
        originalProduct.setProductNum(productRequest.getProductNum());
        originalProduct.setProducer(productRequest.getProducer());

        Boolean success = originalProductDbService.updateById(originalProduct);
        return success ? String.format("产品ID：%s 修改成功",productId):String.format("产品ID：%s 修改失败",productId);
    }

    @Override
    public String delete(Long productId) {

        OriginalProduct originalProduct = originalProductDbService.getById(productId);
        if(originalProduct == null) throw new NotFoundException(ExceptionEnum.NOT_FOUND);

        if(originalProductDbService.removeById(productId)){
            return String.format("产品ID：%s 删除成功",productId);
        }
        return String.format("产品ID：%s 删除失败",productId);
    }

    private void checkDuplicatedProduct(Long productId){
        if(originalProductDbService.countByProductId(productId) > 0){
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);
        }
    }
}
