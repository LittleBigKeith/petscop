import "../styles/Quest.css"

const Quest = () => {
    return (
        <div className="table">
            <table>
                <thead>
                    <tr>
                        <th>Quest</th>
                        <th>Cost</th>
                        <th>Reward</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Alfreds Futterkiste</td>
                        <td>Maria Anders</td>
                        <td>Germany</td>
                    </tr>
                    <tr>
                        <td>Centro comercial Moctezuma</td>
                        <td>Francisco Chang</td>
                        <td>Mexico</td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default Quest;