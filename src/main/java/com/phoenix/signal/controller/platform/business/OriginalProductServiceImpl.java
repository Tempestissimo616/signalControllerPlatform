package com.phoenix.signal.controller.platform.business;

import com.phoenix.signal.controller.platform.dto.request.ProductRequest;
import com.phoenix.signal.controller.platform.exception.ConflictException;
import com.phoenix.signal.controller.platform.model.OriginalProduct;
import com.phoenix.signal.controller.platform.service.OriginalProductDbService;
import com.phoenix.signal.controller.platform.service.OriginalProductDbServiceImpl;
import com.phoenix.signal.controller.platform.type.ExceptionEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public Boolean create(ProductRequest productRequest) {
        checkDuplicatedProduct(productRequest.getProductId());
        OriginalProduct originalProduct = modelMapper.map(productRequest, OriginalProduct.class);

        return originalProductDbService.save(originalProduct);
    }

    private void checkDuplicatedProduct(Long productId){
        if(originalProductDbService.countByProductId(productId) > 0){
            throw new ConflictException(ExceptionEnum.CONFLICT_EXCEPTION);
        }
    }
}
