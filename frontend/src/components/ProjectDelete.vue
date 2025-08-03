<template>

    <v-card outlined>
        <v-card-title>
            ProjectDelete
        </v-card-title>

        <v-card-text>
            <Number label="ProjetId" v-model="value.projetId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="projectDelete"
            >
                ProjectDelete
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
    name: 'ProjectDeleteCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.projetId = 0;
    },
    watch: {
    },
    methods: {
        projectDelete() {
            this.$emit('projectDelete', this.value);
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

