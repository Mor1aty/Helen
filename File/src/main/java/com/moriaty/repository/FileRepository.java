package com.moriaty.repository;

import com.moriaty.entity.File;
import com.moriaty.entity.Filetype;
import com.moriaty.entity.Folder;
import com.moriaty.entity.User;
import org.apache.ibatis.annotations.*;
import org.hibernate.validator.constraints.pl.REGON;

import java.util.List;

public interface FileRepository {

    /**
     * 获取所有文件夹
     *
     * @return List<Folder>
     */
    @Results({@Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "creator", property = "creator", one = @One(select = "com.moriaty.repository.FileRepository.getUserByUsername"))})
    @Select("SELECT * FROM t_folder")
    public List<Folder> getAllFolder();

    // 根据文件夹 id 获取文件
    @Results({@Result(column = "id", property = "id"),
            @Result(column = "location", property = "location"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type", one = @One(select = "com.moriaty.repository.FileRepository.getFiletypeBySuffix")),
            @Result(column = "up", property = "up", one = @One(select = "com.moriaty.repository.FileRepository.getUserByUsername")),
            @Result(column = "up_time", property = "upTime"),
            @Result(column = "folder", property = "folder", one = @One(select = "com.moriaty.repository.FileRepository.getFolderById"))})
    @Select("SELECT * FROM t_file WHERE folder = #{folder}")
    public List<File> getFileByFolder(long folder);

    /**
     * 根据 id 获取文件夹
     *
     * @param id
     * @return Folder
     */
    @Results({@Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "creator", property = "creator", one = @One(select = "com.moriaty.repository.FileRepository.getUserByUsername"))})
    @Select("SELECT * FROM t_folder WHERE id = #{id}")
    public Folder getFolderById(long id);


    /**
     * 根据后缀获取文件类型
     *
     * @param suffix
     * @return Filetype
     */
    @Select("SELECT * FROM t_filetype WHERE suffix = #{suffix}")
    public Filetype getFiletypeBySuffix(String suffix);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return User
     */
    @Select("SELECT * FROM t_user WHERE username = #{username}")
    public User getUserByUsername(String username);

    /**
     * 新建文件夹
     *
     * @param folder
     */
    @Insert("INSERT INTO t_folder(id,name,creator,create_time) VALUES(#{id},#{name},#{creator.username},#{createTime})")
    public void createFolder(Folder folder);

    /**
     * 新建文件
     *
     * @param file
     */
    @Insert("INSERT INTO t_file(id,location,name,type,up,up_time,folder) VALUES(#{id},#{location},#{name}," +
            "#{type.suffix},#{up.username},#{upTime},#{folder.id})")
    public void createFile(File file);

    /**
     * 删除文件
     *
     * @param id
     */
    @Delete("DELETE FROM t_file WHERE id = #{id}")
    public void deleteFile(long id);

    /**
     * 删除文件夹
     *
     * @param id
     */
    @Delete("DELETE FROM t_folder WHERE id = #{id}")
    public void deleteFolder(long id);

    /**
     * 根据文件夹 id 删除文件
     *
     * @param id
     */
    @Delete("DELETE FROM t_file WHERE folder = #{id}")
    public void deleteFileByFolder(long id);

    /**
     * 根据 id 和上传用户名获取文件
     *
     * @param id
     * @param username
     * @return File
     */
    @Results({@Result(column = "id", property = "id"),
            @Result(column = "location", property = "location"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type", one = @One(select = "com.moriaty.repository.FileRepository.getFiletypeBySuffix")),
            @Result(column = "up", property = "up", one = @One(select = "com.moriaty.repository.FileRepository.getUserByUsername")),
            @Result(column = "up_time", property = "upTime"),
            @Result(column = "folder", property = "folder", one = @One(select = "com.moriaty.repository.FileRepository.getFolderById"))})
    @Select("SELECT * FROM t_file WHERE id = #{id} AND up = #{username}")
    public File getFileByIdAndUp(long id, String username);


    /**
     * 根据 id 和创建用户名获取文件夹
     *
     * @param id
     * @param username
     * @return Folder
     */
    @Results({@Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "creator", property = "creator", one = @One(select = "com.moriaty.repository.FileRepository.getUserByUsername"))})
    @Select("SELECT * FROM t_folder WHERE id = #{id} AND creator = #{username}")
    public Folder getFolderByIdAndCreator(long id, String username);


    /**
     *  获取所有文件类型
     * @return List<Filetype>
     */
    @Select("SELECT * FROM t_filetype")
    public List<Filetype> getAllFiletype();


}
