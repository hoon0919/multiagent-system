<template>
    <div>
        <Number
            label="PostId"
            v-model="value.postId"
            :editMode="editMode"
        />
        <String
            label="Title"
            v-model="value.title"
            :editMode="editMode"
        />
        <String
            label="Content"
            v-model="value.content"
            :editMode="editMode"
        />
        <Number
            label="ViewCount"
            v-model="value.viewCount"
            :editMode="editMode"
        />
        <Date
            label="CreatedAt"
            v-model="value.createdAt"
            :editMode="editMode"
        />
        <Date
            label="UpdatedAt"
            v-model="value.updatedAt"
            :editMode="editMode"
        />
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <div variant="outlined" class="my-2">
            <FileUrl v-model="fileUrlInput"
                offline
                label="FileUrl"
                :editMode="editMode"
                @change="change"
            />
            <v-row class="ma-0 pa-0 mt-2">
                <v-spacer></v-spacer>
                <v-btn @click="addFileUrlList">FileUrl Add</v-btn>
            </v-row>
            <v-card v-if="value.fileUrl.length > 0" variant="outlined" class="pa-4 mt-2">
                <v-card-sub-title>
                    FileUrl List
                </v-card-sub-title>
                <li v-for="(id, index) in value.fileUrl" :key="index">
                    {{ id && id. ? id. : '' }}
                </li>
            </v-card>
        </div>
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Post',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "posts",
        fileUrlInput: null,
        value: {
            fileUrl: [],
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        addFileUrlList() {
            if (this.fileUrlInput !== null && this.fileUrlInput !== '') {
                this.value.fileUrl.push(this.fileUrlInput);
                this.fileUrlInput = null; // null로 초기화
            }
        },
    },
}
</script>
