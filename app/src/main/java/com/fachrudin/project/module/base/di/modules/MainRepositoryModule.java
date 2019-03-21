package com.fachrudin.project.module.base.di.modules;

import com.fachrudin.project.core.common.protocol.Mapper;
import com.fachrudin.project.core.di.ApplicationScope;
import com.fachrudin.project.core.webapi.ServiceFactory;
import com.fachrudin.project.module.biz.entities.main.UserList;
import com.fachrudin.project.module.biz.repositories.MainRepository;
import com.fachrudin.project.module.data.datasource.webapi.dto.main.UserListDto;
import com.fachrudin.project.module.data.datasource.webapi.services.MainApiService;
import com.fachrudin.project.module.data.mappers.main.UserListDtoMapper;
import com.fachrudin.project.module.data.repositories.MainRepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
@Module
public class MainRepositoryModule {

    @Provides
    @ApplicationScope
    MainApiService provideMainApiService(ServiceFactory serviceFactory) {
        return serviceFactory.createService(MainApiService.class);
    }

    @Provides
    @ApplicationScope
    MainRepository provideMemberRepository(MainApiService service,
                                           Mapper<UserListDto, UserList> userMapper) {
        return new MainRepositoryImpl(service, userMapper);
    }

    @Provides
    @ApplicationScope
    Mapper<UserListDto, UserList> provideUserListDtoMapper() {
        return new UserListDtoMapper();
    }
}
