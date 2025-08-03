<template>

    <v-card outlined>
        <v-card-title>
            CreateProject
        </v-card-title>

        <v-card-text>
            <Number label="ProjetId" v-model="value.projetId" :editMode="editMode"/>
            <String label="ProjectName" v-model="value.projectName" :editMode="editMode"/>
            <String label="ProjectDescription" v-model="value.projectDescription" :editMode="editMode"/>
            <AttachmentList offline label="Attachments" v-model="value.attachments" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createProject"
            >
                CreateProject
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'CreateProjectCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.projetId = 0;
        this.value.projectName = '';
        this.value.projectDescription = '';
        this.value.attachments = [];
    },
    watch: {
    },
    methods: {
        createProject() {
            this.$emit('createProject', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

