package com.avinnikov.behance.data.equality

import com.avinnikov.behance.common.equality.Equality
import com.avinnikov.behance.data.local.Project

class ProjectEquality : Equality<Project> {
    override fun getContentEquality(): (Project, Project) -> Boolean {
        return { a: Project, b: Project -> a.name == b.name }
    }

    override fun getIdentityEquality(): (Project, Project) -> Boolean {
        return { a: Project, b: Project -> a.id == b.id }
    }
}