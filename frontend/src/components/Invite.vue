<template>
    <div>
        <Number
            label="InviteId"
            v-model="value.inviteId"
            :editMode="editMode"
        />
        <Number
            label="ProjectId"
            v-model="value.projectId"
            :editMode="editMode"
        />
        <String
            label="Email"
            v-model="value.email"
            :editMode="editMode"
        />
        <String
            label="Status"
            v-model="value.status"
            :editMode="editMode"
        />
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
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
    name: 'Invite',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "invites",
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
