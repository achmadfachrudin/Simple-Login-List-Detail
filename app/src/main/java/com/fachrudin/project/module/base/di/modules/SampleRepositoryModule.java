package com.fachrudin.project.module.base.di.modules;

import com.fachrudin.project.core.common.protocol.Mapper;
import com.fachrudin.project.core.di.ApplicationScope;
import com.fachrudin.project.core.webapi.ServiceFactory;
import com.fachrudin.project.module.biz.entities.sample.SampleTest;
import com.fachrudin.project.module.biz.repositories.SampleRepository;
import com.fachrudin.project.module.data.datasource.webapi.dto.sample.SampleTestDto;
import com.fachrudin.project.module.data.datasource.webapi.services.SampleApiService;
import com.fachrudin.project.module.data.mappers.sample.SampleTestDtoMapper;
import com.fachrudin.project.module.data.repositories.SampleRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@Module
public class SampleRepositoryModule {

    @Provides
    @ApplicationScope
    SampleApiService provideSampleApiService(ServiceFactory serviceFactory) {
        return serviceFactory.createService(SampleApiService.class);
    }

    @Provides
    @ApplicationScope
    SampleRepository provideMemberRepository(SampleApiService service,
                                               Mapper<SampleTestDto, SampleTest> sampleMapper) {
        return new SampleRepositoryImpl(service, sampleMapper);
    }

    @Provides
    @ApplicationScope
    Mapper<SampleTestDto, SampleTest> provideSampleTestDtoMapper() {
        return new SampleTestDtoMapper();
    }
}
