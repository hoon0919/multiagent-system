<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="increaseViewCountDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>조회수 증가
                </v-btn>
                <v-dialog v-model="increaseViewCountDialog" width="500">
                    <IncreaseViewCount
                        @closeDialog="increaseViewCountDialog = false"
                        @increaseViewCount="increaseViewCount"
                    ></IncreaseViewCount>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="editPostDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>게시물 수정
                </v-btn>
                <v-dialog v-model="editPostDialog" width="500">
                    <EditPost
                        @closeDialog="editPostDialog = false"
                        @editPost="editPost"
                    ></EditPost>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="createPostDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>게시물 생성
                </v-btn>
                <v-dialog v-model="createPostDialog" width="500">
                    <CreatePost
                        @closeDialog="createPostDialog = false"
                        @createPost="createPost"
                    ></CreatePost>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="postDeleteDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>게시물 삭제
                </v-btn>
                <v-dialog v-model="postDeleteDialog" width="500">
                    <PostDelete
                        @closeDialog="postDeleteDialog = false"
                        @postDelete="postDelete"
                    ></PostDelete>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>ViewCount</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        <th>UserId</th>
                        <th>FileUrl</th>
                        <th>Blob Storage</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="Title">{{ val.title }}</td>
                            <td class="whitespace-nowrap" label="Content">{{ val.content }}</td>
                            <td class="whitespace-nowrap" label="ViewCount">{{ val.viewCount }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="Blob Storage">
                                <BlobStorageId :editMode="editMode" v-model="val.blobStorageId"></BlobStorageId>
                            </td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <FileDetailGrid style="margin-top: 20px;" label="FileUrl" offline v-if="selectedRow" v-model="selectedRow.fileUrl" :selectedRow="selectedRow"/>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Post 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Post :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Post 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="PostId" v-model="selectedRow.postId" :editMode="true"/>
                            <String label="Title" v-model="selectedRow.title" :editMode="true"/>
                            <String label="Content" v-model="selectedRow.content" :editMode="true"/>
                            <Number label="ViewCount" v-model="selectedRow.viewCount" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <Number label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <FileDetailGrid label="FileUrl" offline v-model="selectedRow.fileUrl" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'postGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'posts',
        increaseViewCountDialog: false,
        editPostDialog: false,
        createPostDialog: false,
        postDeleteDialog: false,
    }),
    watch: {
    },
    methods:{
        async increaseViewCount(params){
            try{
                var path = "increaseViewCount".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','increaseViewCount 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.increaseViewCountDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async editPost(params){
            try{
                var path = "editPost".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','editPost 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.editPostDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async createPost(params){
            try{
                var path = "createPost".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','createPost 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.createPostDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async postDelete(params){
            try{
                var path = "postDelete".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','postDelete 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.postDeleteDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>