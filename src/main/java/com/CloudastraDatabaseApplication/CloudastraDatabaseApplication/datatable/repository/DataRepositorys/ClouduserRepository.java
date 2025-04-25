package com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.repository.DataRepositorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.CloudastraDatabaseApplication.CloudastraDatabaseApplication.datatable.model.DataEntitys.Clouduser;

@Repository
public interface ClouduserRepository extends JpaRepository<Clouduser, Long> {
}
