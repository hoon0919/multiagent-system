<template>

    <v-card outlined>
        <v-card-title>
            CreateComment
        </v-card-title>

        <v-card-text>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <Number label="PostId" v-model="value.postId" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createComment"
            >
                CreateComment
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
    name: 'CreateCommentCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.content = '';
        this.value.postId = 0;
        this.value.content = '';
    },
    watch: {
    },
    methods: {
        createComment() {
            this.$emit('createComment', this.value);
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

