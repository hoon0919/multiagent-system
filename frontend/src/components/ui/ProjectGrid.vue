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
                <v-btn style="margin-left: 5px;" @click="memberInviteDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>팀원 초대
                </v-btn>
                <v-dialog v-model="memberInviteDialog" width="500">
                    <MemberInvite
                        @closeDialog="memberInviteDialog = false"
                        @memberInvite="memberInvite"
                    ></MemberInvite>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="createProjectDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>프로젝트 생성
                </v-btn>
                <v-dialog v-model="createProjectDialog" width="500">
                    <CreateProject
                        @closeDialog="createProjectDialog = false"
                        @createProject="createProject"
                    ></CreateProject>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="projectDeleteDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>프로젝트 종료
                </v-btn>
                <v-dialog v-model="projectDeleteDialog" width="500">
                    <ProjectDelete
                        @closeDialog="projectDeleteDialog = false"
                        @projectDelete="projectDelete"
                    ></ProjectDelete>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>ProjectName</th>
                        <th>ProjectDescription</th>
                        <th>Attachments</th>
                        <th>MembersEmail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="ProjectName">{{ val.projectName }}</td>
                            <td class="whitespace-nowrap" label="ProjectDescription">{{ val.projectDescription }}</td>
                            <td class="whitespace-nowrap" label="Attachments">{{ val.attachments }}</td>
                            <td class="whitespace-nowrap" label="MembersEmail">{{ val.membersEmail }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Project 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Project :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Project 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="ProjetId" v-model="selectedRow.projetId" :editMode="true"/>
                            <String label="ProjectName" v-model="selectedRow.projectName" :editMode="true"/>
                            <String label="ProjectDescription" v-model="selectedRow.projectDescription" :editMode="true"/>
                            <TeamMembers offline label="MembersEmail" v-model="selectedRow.membersEmail" :editMode="true"/>
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
    name: 'projectGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'projects',
        memberInviteDialog: false,
        createProjectDialog: false,
        projectDeleteDialog: false,
    }),
    watch: {
    },
    methods:{
        async memberInvite(params){
            try{
                var path = "memberInvite".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','memberInvite 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.memberInviteDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async createProject(params){
            try{
                var path = "createProject".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','createProject 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.createProjectDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async projectDelete(params){
            try{
                var path = "projectDelete".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','projectDelete 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.projectDeleteDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>