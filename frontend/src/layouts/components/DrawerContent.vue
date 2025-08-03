<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '초대관리' }" />
        <VerticalNavLink
            :item="{
                title: 'Invite',
                to: '/invites',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '프로젝트 관리' }" />
        <VerticalNavLink
            :item="{
                title: 'Project',
                to: '/projects',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '회원관리' }" />
        <VerticalNavLink
            :item="{
                title: '사용자',
                to: '/users',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '문서 게시판 관리' }" />
        <VerticalNavLink
            :item="{
                title: '게시글',
                to: '/posts',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '첨부파일 관리' }" />
        <VerticalNavLink
            :item="{
                title: '첨부파일',
                to: '/attachments',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '댓글 관리' }" />
        <VerticalNavLink
            :item="{
                title: '댓글',
                to: '/comments',
            }"
        />
    </ul>
</template>
