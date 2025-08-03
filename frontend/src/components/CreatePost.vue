<template>

    <v-card outlined>
        <v-card-title>
            CreatePost
        </v-card-title>

        <v-card-text>
            <Number label="PostId" v-model="value.postId" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <Number label="ViewCount" v-model="value.viewCount" :editMode="editMode"/>
            <Date label="CreatedAt" v-model="value.createdAt" :editMode="editMode"/>
            <Date label="UpdatedAt" v-model="value.updatedAt" :editMode="editMode"/>
            <Number label="UserId" v-model="value.userId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createPost"
            >
                CreatePost
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
    name: 'CreatePostCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.postId = 0;
        this.value.title = '';
        this.value.content = '';
        this.value.viewCount = 0;
        this.value.createdAt = '2025-08-03';
        this.value.updatedAt = '2025-08-03';
        this.value.userId = 0;
    },
    watch: {
    },
    methods: {
        createPost() {
            this.$emit('createPost', this.value);
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

