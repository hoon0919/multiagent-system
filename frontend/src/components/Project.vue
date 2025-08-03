<template>
    <div>
        <Number
            label="ProjetId"
            v-model="value.projetId"
            :editMode="editMode"
        />
        <String
            label="ProjectName"
            v-model="value.projectName"
            :editMode="editMode"
        />
        <String
            label="ProjectDescription"
            v-model="value.projectDescription"
            :editMode="editMode"
        />
        <TeamMembers
            offline
            label="MembersEmail"
            v-model="value.membersEmail"
            :editMode="editMode"
            @change="change"
        />
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
    name: 'Project',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "projects",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
