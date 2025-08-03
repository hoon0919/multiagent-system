<template>

    <v-card outlined>
        <v-card-title>
            IncreaseViewCount
        </v-card-title>

        <v-card-text>
            <Number label="PostId" v-model="value.postId" :editMode="editMode"/>
            <Number label="ViewCount" v-model="value.viewCount" :editMode="editMode"/>
            <Number label="UserId" v-model="value.userId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="increaseViewCount"
            >
                IncreaseViewCount
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
    name: 'IncreaseViewCountCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.postId = 0;
        this.value.viewCount = 0;
        this.value.userId = 0;
    },
    watch: {
    },
    methods: {
        increaseViewCount() {
            this.$emit('increaseViewCount', this.value);
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

