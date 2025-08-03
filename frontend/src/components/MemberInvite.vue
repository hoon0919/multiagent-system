<template>

    <v-card outlined>
        <v-card-title>
            MemberInvite
        </v-card-title>

        <v-card-text>
            <Number label="ProjetId" v-model="value.projetId" :editMode="editMode"/>
            <TeamMembers offline label="MembersEmail" v-model="value.membersEmail" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="memberInvite"
            >
                MemberInvite
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
    name: 'MemberInviteCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.projetId = 0;
        this.value.membersEmail = {};
    },
    watch: {
    },
    methods: {
        memberInvite() {
            this.$emit('memberInvite', this.value);
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

